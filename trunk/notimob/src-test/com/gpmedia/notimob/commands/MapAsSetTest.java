package com.gpmedia.notimob.commands;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.gpmedia.notimob.model.Pair;
import com.gpmedia.notimob.systems.MapAsSet;

public class MapAsSetTest {
	
	@Test
	public void testMapAsSet() throws Exception {
		MapAsSet data = new MapAsSet ();
		data.put ("test", "data");
		data.put ("test2", "data2");
		Set<Pair> set = data.asSet ();
		Assert.assertTrue (set.size() == 2);
		Assert.assertTrue (set.contains(new Pair ("test", "data")));
		Assert.assertTrue (set.contains(new Pair ("test2", "data2")));
		
	}
}
