package org.lanqiao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Cart;
import org.lanqiao.entity.CookieItem;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.entity.User;
import org.lanqiao.service.OrderDetailService;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.impl.OrderDetailServiceImpl;
import org.lanqiao.service.impl.OrderServiceImpl;
import org.lanqiao.util.CartUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet(name = "cart.do", urlPatterns = { "/cart.do" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		// 1、将商品添加到购物车(cookie)|更新购物车的商品//{gid:1,amount:3}
		String gid = request.getParameter("gid");
		// System.out.println(gid);
		if (type != null && type.equals("add")) {
			CookieItem item = new CookieItem(gid, 1);
			addItem(item, request, response);
			// 2、获取购物车中所有商品
			// List<CookieItem> cart=getItems(request);
			// 3、存到request域对象，转到cart.jsp显示车中的商品
			// List<Cart> list=CartUtil.convertCookieItemListToCartList(cart);
			// request.setAttribute("cart", list);
			request.getRequestDispatcher("WEB-INF/addsuccess.jsp").forward(
					request, response);
		} else if (type != null && type.equals("remove")) {
			removeItem(gid, request, response);
			request.getRequestDispatcher("WEB-INF/removesuccess.jsp").forward(
					request, response);
		} else if (type != null && type.equals("final")) {
			List<CookieItem> list = getItems(request);

			// 购物车是否为空
			// 判断session是否为空
			Cookie[] cookies1 = request.getCookies();
			// System.out.println(cookies1);
			Object us = request.getSession().getAttribute("user");
			// System.out.println(us);
			if (us == null && cookies1 == null) {
				// 未登陆 session和cookie都为空
				// 跳转到提示登陆界面

				request.getRequestDispatcher("WEB-INF/pleaselogin.jsp")
						.forward(request, response);
			} else {
				if (list == null) {
					// 如果购物车为空 跳转到提示选购界面
					request.getRequestDispatcher("WEB-INF/pleaseshop.jsp")
							.forward(request, response);
				}
				// 应该是在点击提交订单才进行下订单操作
				// 1.下订单
				else {
					List<Cart> buygoods = CartUtil
							.convertCookieItemListToCartList(list);
					String orderid = UUID.randomUUID().toString();
					// 如果session为空
					Cookie currentcart = null;
					String userid = null;
					if (us == null) {
						for (Cookie c : cookies1) {
							if (c.getName().equals("uloginid")) {
								currentcart = c;
								userid = currentcart.getValue();
								break;
							}
						}
					} else {
						userid = ((User) request.getSession().getAttribute(
								"user")).getUserid();
					}
					double totalprice = 0;
					for (int i = 0; i < buygoods.size(); i++) {
						Cart cart = buygoods.get(i);
						totalprice += cart.getAmount() * cart.getGsaleprice();
					}
					Date orderdate = new Date();
					Order order = new Order(orderid, null, userid, totalprice,
							orderdate);
					OrderService os = new OrderServiceImpl();
					os.insertOrder(order);
					OrderDetailService ods = new OrderDetailServiceImpl();
					for (Cart c : buygoods) {
						String orderdetailid = UUID.randomUUID().toString();
						String gtitle = c.getGtitle();
						Double gsaleprice = c.getGsaleprice();
						int gnumber = c.getAmount();
						OrderDetail orderDetail = new OrderDetail(
								orderdetailid, gtitle, gsaleprice, gnumber,
								orderid);
						ods.insertOrderDetail(orderDetail);
					}
					request.getRequestDispatcher("WEB-INF/order.jsp").forward(
							request, response);

				}

			}
		} else if (type != null && type.equals("ofinal")) {
			request.getRequestDispatcher("WEB-INF/orderfinal.jsp").forward(
					request, response);
		} else if (type != null && type.equals("osuccess")) {
			// 清空购物车
			Cookie[] cookies = request.getCookies();
			Cookie currentcart = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("cart")) {
						currentcart = c;
						break;
					}
				}
				if (currentcart != null) {
					currentcart.setMaxAge(0);
					response.addCookie(currentcart);
				}
			}
			request.getRequestDispatcher("WEB-INF/ordersuccess.jsp").forward(
					request, response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// 从购物车(cookie)中取出所有商品
	private List<CookieItem> getItems(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		Cookie cart = null;
		for (Cookie c : cookies) {
			if (c.getName().equals("cart")) {
				cart = c;
			}
		}
		if (cart == null) {
			return null;
		}
		String json = cart.getValue();
		Gson gson = new Gson();
		TypeToken<List<CookieItem>> listType = new TypeToken<List<CookieItem>>() {
		};
		List<CookieItem> list = gson.fromJson(json, listType.getType());
		return list;
	}

	// 将商品添加到购物车
	private void addItem(CookieItem item, HttpServletRequest request,
			HttpServletResponse response) {
		List<CookieItem> list = getItems(request);
		// 第一次向购物车添加商品
		if (list == null) {
			list = new ArrayList<CookieItem>();
			// list.add(item);
		}
		// 表示购物车不为空（有商品）
		if (list != null) {
			CookieItem currentItem = null;
			for (CookieItem goods : list) {
				if (goods.getGid().equals(item.getGid())) {// 存在此商品
					currentItem = goods;
					break;
				}
			}
			if (currentItem == null) {// 说明购物车中无此商品
				list.add(item);
			} else {
				currentItem.setAmount(currentItem.getAmount() + 1);// 只更新商品数量

			}
		}
		// 重新将数据写入到cookie
		Gson gson = new Gson();
		String json = gson.toJson(list);
		Cookie cookie = new Cookie("cart", json);
		cookie.setMaxAge(60 * 60 * 24 * 365);
		response.addCookie(cookie);
	}

	// 从购物车中删除商品
	private void removeItem(String gid, HttpServletRequest request,
			HttpServletResponse response) {
		List<CookieItem> list = getItems(request);
		if (list == null) {
			return;
		}

		CookieItem currentItem = null;
		for (CookieItem goods : list) {
			if (goods.getGid().equals(gid)) {// 存在此商品
				currentItem = goods;
				break;
			}
		}
		if (currentItem == null) {
			return;
		} else {
			list.remove(currentItem);
		}
		// 重新写入cookie
		Gson gson = new Gson();
		String json = gson.toJson(list);
		Cookie cookie = new Cookie("cart", json);
		cookie.setMaxAge(60 * 60 * 24 * 365);
		response.addCookie(cookie);
	}
}
