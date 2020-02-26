package com.ednilson.pedidos.dto;

import java.io.Serializable;

import com.ednilson.pedidos.domain.Cliente;
import com.ednilson.pedidos.domain.Pedido;

public class PedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Cliente cliente;

	public PedidoDTO(Pedido pedido) {
		id = pedido.getId();
		cliente = pedido.getCliente();
	}	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
