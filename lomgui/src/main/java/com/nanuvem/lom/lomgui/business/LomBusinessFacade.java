package com.nanuvem.lom.lomgui.business;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.node.ObjectNode;

public class LomBusinessFacade {
	
	private static LomBusinessFacade singleton;
	
	public static LomBusinessFacade getInstance(){
		if(LomBusinessFacade.singleton == null){
			LomBusinessFacade.singleton = new LomBusinessFacade();
		}
		return LomBusinessFacade.singleton;
	}
	
	private Map<Long, Clazz> classes;
	
	private LomBusinessFacade() {
		this.classes = new HashMap<Long, Clazz>();
		this.mock();
	}

	private void mock() {
		String[] classNames = { "Cliente", "Produto", "Funcionario",
				"Fornecedor" };
		for (int i = 0; i <= classNames.length - 1; i++) {
			Clazz clazz = new Clazz((long) i + 1, classNames[i]);
			this.addClazz(clazz);
		}
		mockCliente();
		mockProduto();
		mockFuncionario();
		mockFornecedor();
	}

	private void mockCliente() {
		Clazz clienteClazz = this.getClazz("Cliente");
		clienteClazz.addAttribute("Nome", "String");
		clienteClazz.addAttribute("CPF", "String");
		clienteClazz.addAttribute("DataNascimento", "Date");
		
		Instance instance1 = clienteClazz.createInstance();
		instance1.setValue("Nome", "Jose");
		instance1.setValue("CPF", "0001");
		instance1.setValue("DataNascimento", "01/01/11");
		
		Instance instance2 = clienteClazz.createInstance();
		instance2.setValue("Nome", "Maria");
		instance2.setValue("CPF", "0002");
//		instance2.setValue("DataNascimento", "02/01/11");
		
	}
	
	private void mockProduto() {
		Clazz produtoClazz = this.getClazz("Produto");
		produtoClazz.addAttribute("Nome", "String");
		produtoClazz.addAttribute("Valor", "Float");
	}
	
	private void mockFuncionario() {
		Clazz produtoClazz = this.getClazz("Funcionario");
		produtoClazz.addAttribute("Nome", "String");
		produtoClazz.addAttribute("Salario", "Float");
	}
	
	private void mockFornecedor() {
		Clazz produtoClazz = this.getClazz("Fornecedor");
		produtoClazz.addAttribute("Nome", "String");
	}
	
	public void addClazz(Clazz clazz){
		this.classes.put(clazz.getId(), clazz);
	}
	
	public void addClazz(ObjectNode clazzNode){
		clazzNode.put("id", classes.size() + 1);
		addClazz(Clazz.clazzFromJson(clazzNode));
	}
	
	public Clazz getClazz(String name){
		for(Clazz clazz : this.classes.values()){
			if(clazz.getName().equals(name)){
				return clazz;
			}
		}
		return null;
	}
	
	public Clazz getClazz(Long id){
		return this.classes.get(id);
	}
	
	public Collection<Clazz> getAllClazz(){
		return classes.values();
	}
	
	public void removeClazz(String name){
		for(Clazz clazz : this.classes.values()){
			if(clazz.getName().equals(name)){
				removeClazz(clazz.getId());
			}
		}
	}
	
	public void removeClazz(Long id){
		this.classes.remove(id);
	}
	
	public void removeAllClazz(){
		this.classes.clear();
	}
	
	
}
