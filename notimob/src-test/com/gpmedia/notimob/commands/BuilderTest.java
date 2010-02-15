package com.gpmedia.notimob.commands;

import junit.framework.Assert;

import org.junit.Test;

import com.gpmedia.notimob.model.Builder;
import com.gpmedia.notimob.model.Plugin;

public class BuilderTest {
	@Test
	public void testBuilder() throws Exception {
		Plugin plugin = new Builder<Plugin>(new Plugin()).
		icon("/img/vkontakte.gif").
		link("http://vkontakte.ru").
		title("Vkontakte.ru").
		alias("vkontakte").
		instance();
		
		Assert.assertNotNull(plugin);
		Assert.assertEquals("/img/vkontakte.gif", plugin.getIcon());
		Assert.assertEquals("http://vkontakte.ru", plugin.getLink());		
		Assert.assertEquals("Vkontakte.ru", plugin.getTitle());		
		Assert.assertEquals("vkontakte", plugin.getAlias());
	}
}
