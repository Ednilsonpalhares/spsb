package com.ednilson.pedidos;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ednilson.pedidos.domain.Categoria;
import com.ednilson.pedidos.domain.Cidade;
import com.ednilson.pedidos.domain.Cliente;
import com.ednilson.pedidos.domain.Endereco;
import com.ednilson.pedidos.domain.Estado;
import com.ednilson.pedidos.domain.ItemPedido;
import com.ednilson.pedidos.domain.Pagamento;
import com.ednilson.pedidos.domain.PagamentoComBoleto;
import com.ednilson.pedidos.domain.PagamentoComCartao;
import com.ednilson.pedidos.domain.Pedido;
import com.ednilson.pedidos.domain.Produto;
import com.ednilson.pedidos.domain.enums.EstadoPagamento;
import com.ednilson.pedidos.domain.enums.TipoCliente;
import com.ednilson.pedidos.repositories.CategoriaRepository;
import com.ednilson.pedidos.repositories.CidadeRepository;
import com.ednilson.pedidos.repositories.ClienteRepository;
import com.ednilson.pedidos.repositories.EnderecoRepository;
import com.ednilson.pedidos.repositories.EstadoRepository;
import com.ednilson.pedidos.repositories.ItemPedidoRepository;
import com.ednilson.pedidos.repositories.PagamentoRepository;
import com.ednilson.pedidos.repositories.PedidoRepository;
import com.ednilson.pedidos.repositories.ProdutoRepository;

@SpringBootApplication
public class SpsbApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpsbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));	
		
		categoriaRepo.saveAll(Arrays.asList(cat1,cat2));
		produtoRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia",est1);
		Cidade c2 = new Cidade(null, "São paulo",est2);
		Cidade c3 = new Cidade(null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepo.saveAll(Arrays.asList(est1,est2));
		
		cidadeRepo.saveAll(Arrays.asList(c1,c2));
		
		Cliente cli1 = new Cliente(null,"Maria silva", "mria@gmail.com","70441269010", TipoCliente.PESSOA_FISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("991540956", "991540957"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim","5918200", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "37", "Apto 309", "Jardim","5918300", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.save(cli1);
		
		enderecoRepo.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 19:30"), cli1, e1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 18:54"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
	
		itemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
	
}
