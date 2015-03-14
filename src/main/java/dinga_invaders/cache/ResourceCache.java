package dinga_invaders.cache;

import java.net.URL;
import java.util.HashMap;

public abstract class ResourceCache {

	protected HashMap<String, Object> resources;

	public ResourceCache() {
		resources = new HashMap<String, Object>();
	}

	protected Object loadResource(String name) {
		URL url = null;
		url = getClass().getClassLoader().getResource(name);
		return loadResource(url);
	}

	protected Object getResource(String name) {
		Object resource = resources.get(name);
		if (resource == null) {
			resource = loadResource(name);
			resources.put(name, resource);
		}
		return resource;
	}

	protected abstract Object loadResource(URL url);
}
