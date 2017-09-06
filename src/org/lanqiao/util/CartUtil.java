package org.lanqiao.util;

import java.util.ArrayList;
import java.util.List;

import org.lanqiao.entity.Cart;
import org.lanqiao.entity.CookieItem;
import org.lanqiao.entity.Goods;
import org.lanqiao.service.GoodService;
import org.lanqiao.service.impl.GoodServiceImpl;

public class CartUtil {
	public static List<Cart> convertCookieItemListToCartList(List<CookieItem>list){		
		if(list==null) return null;
		List<Cart> cart=new ArrayList<Cart>();
		Cart c=null;
		GoodService gs=new GoodServiceImpl();
		for (int i = 0; i < list.size(); i++) {
			CookieItem item =list.get(i);
			Goods goods=gs.getGoodsBygid(item.getGid());
			c =new Cart(item.getGid(), goods.getGtitle(), goods.getGsaleprice(), goods.getGinprice(), item.getAmount());			cart.add(c);
		}
		return cart;
	}
}
