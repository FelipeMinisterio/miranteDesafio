package br.com.config;

import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application{

	@Override
	public Set<Class<?>> getClasses() {
	Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
	addRestResourceClasses(resources);
		return resources;
	}
	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(br.com.service.OperadorService.class);
		resources.add(br.com.service.PerfilService.class);
		resources.add(br.com.service.PessoaService.class);
		resources.add(br.com.service.LoginService.class);
		resources.add(br.com.service.TelefoneService.class);
	}
	
}
