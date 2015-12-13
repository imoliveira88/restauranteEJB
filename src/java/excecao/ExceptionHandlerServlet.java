package excecao;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandlerServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processError(request,response);
        }

    protected void processError(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception"); 
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) { 
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) { 
            requestUri = "Unknown";
        } 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        out.write("<html><head><title>Exception/Error Details</title></head><body>");
        if(statusCode == 404){
            out.write("<h1>Página não encontrada. Clique <a href='home.xhtml'>aqui</a> para voltar à Home Page.</h1>");
         }
        else{
            out.write("<h1>Ocorreu um erro. Clique <a href='home.xhtml'>aqui</a> para voltar à Home Page.</h1>");
        }
        out.write("</body></html>");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}