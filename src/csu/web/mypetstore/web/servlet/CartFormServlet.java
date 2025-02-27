package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.impl.CartDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartFormServlet extends HttpServlet {
    CartDao cartDao = new CartDaoImpl();

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        if(account == null)
        {
            resp.sendRedirect("signOnForm");
        }
        else {
            Cart cart = cartDao.getCart(account.getUsername());
            session.setAttribute("cart", cart);
            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        }
    }
}
