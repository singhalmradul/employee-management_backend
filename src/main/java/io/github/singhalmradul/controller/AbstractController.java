package io.github.singhalmradul.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.singhalmradul.dao.AbstractDao;
import io.github.singhalmradul.entity.AbstractEntity;
import io.github.singhalmradul.utils.HibernateUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AbstractController<E extends AbstractEntity, D extends AbstractDao<E>>
        extends HttpServlet {
    private Session session;
    protected transient D dao;
    private Class<D> daoClass;
    private Class<E> entityClass;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(mapper.writeValueAsString("Invalid request"));
            out.flush();
            out.close();
            return;
        }
        try {
            UUID id = UUID.fromString(pathInfo.split("/")[1]);
            dao.delete(id);
            out.print(mapper.writeValueAsString(id));
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(mapper.writeValueAsString(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(mapper.writeValueAsString(e.getMessage()));
        } finally {
            out.flush();
            out.close();
        }
        super.doDelete(req, resp);
    }

    /**
     * @param entityClass
     */
    public AbstractController(Class<E> entityClass, Class<D> daoClass) {
        this.daoClass = daoClass;
        this.entityClass = entityClass;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<E> entities = dao.getAll();
                out.print(mapper.writeValueAsString(entities));
            } else {
                UUID id = UUID.fromString(pathInfo.split("/")[1]);
                E entity = dao.getById(id);
                out.print(mapper.writeValueAsString(entity));
            }
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(mapper.writeValueAsString(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(mapper.writeValueAsString(e.getMessage()));
        } finally {
            out.flush();
            out.close();
        }
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && !pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(mapper.writeValueAsString("Invalid request"));
            out.flush();
            out.close();
            return;
        }
        try {
            E entity = mapper.readValue(req.getInputStream(), entityClass);
            dao.save(entity);
            out.print(mapper.writeValueAsString(entity));
        } catch (HibernateException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(mapper.writeValueAsString(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(mapper.writeValueAsString(e.getMessage()));
        } finally {
            out.flush();
            out.close();
        }
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(mapper.writeValueAsString("Invalid request"));
            out.flush();
            out.close();
            return;
        }
        try {
            E entity = mapper.readValue(req.getInputStream(), entityClass);
            dao.update(entity);
            out.print(mapper.writeValueAsString(entity));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(mapper.writeValueAsString(e.getMessage()));
        } finally {
            out.flush();
            out.close();
        }
        super.doPut(req, resp);
    }

    @Override
    public void destroy() {
        session.close();
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        session = HibernateUtils.getInstance().getSession();
        try {
            dao = daoClass.getConstructor(Session.class).newInstance(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
