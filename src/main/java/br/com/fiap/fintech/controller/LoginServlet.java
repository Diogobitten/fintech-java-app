package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.bo.EmailBo;
import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.exception.EmailException;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsuarioDao usuarioDao;
    private EmailBo bo;

    public LoginServlet() {
        usuarioDao = DaoFactory.getUsuarioDao();
        bo = new EmailBo();
        System.out.println("abc");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");

        Usuario usuario = new Usuario(email, senha);
        System.out.println("aaaaaa");

        if (usuarioDao.validarUsuario(usuario)) {

            HttpSession session = request.getSession();
            System.out.println("UI");
            session.setAttribute("user", email);
            String mensagem = "Um login foi realizado em " + LocalDate.now();
            System.out.println("eeeee");
            try {
                bo.enviarEmail(email, "Login realizado", mensagem);
                System.out.println("rrr");
            } catch (EmailException e) {
                e.printStackTrace();
            }
            // Redireciona para o dashboard
            request.getRequestDispatcher("dash3.jsp").forward(request, response);
        } else {
            // Se login falhar, exibe mensagem de erro
            request.setAttribute("erro", "Usuário ou senha incorretos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("QQQQ");
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}
