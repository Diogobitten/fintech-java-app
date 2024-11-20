package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.CadastrarUsuarioDao;
import br.com.fiap.fintech.exception.DBEexception;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Investimento;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.util.CriptografiaUtils;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@WebServlet("/cadastro")

public class CadastroServlet extends HttpServlet {

    private CadastrarUsuarioDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getCadastrarUsuarioDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Requisição recebida no CadastroServlet");

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String genero = req.getParameter("genero");
        LocalDate dataNascimento = LocalDate.parse(req.getParameter("dataNascimento"));
        System.out.println("Requisição recebida no CadastroServlet");


        String senhaCriptografada = null;
        try {
            senhaCriptografada = CriptografiaUtils.criptografar(senha);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao criptografar senha");
            req.getRequestDispatcher("cadastro-usuario.jsp").forward(req, resp);
            return;
        }

        Usuario usuario = new Usuario(
                nome,
                email,
                senhaCriptografada, // Agora a senha está criptografada
                genero,
                dataNascimento
        );

        try {
            dao.cadastrarUsuario(usuario);
            req.setAttribute("mensagem", "Cadastrado com sucesso!");
        } catch (DBEexception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar usuario");
        }
        req.getRequestDispatcher("cadastro-usuario.jsp").forward(req, resp);
    }

}
