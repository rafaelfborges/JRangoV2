package menu;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import empresa.Colaborador;
import empresa.SistemaGestao;
import empresa.Usuario;
import fluxo.Fluxo;
import operadora.Cartao;
import operadora.Consumo;
import operadora.GestaoCartao;
import comercio.Churrascaria;
import comercio.Comercio;
import comercio.Estabelecimento;
import comercio.Papelaria;
import comercio.Pizzaria;
import comercio.Produto;
import comercio.Restaurante;
import tratamento.CartaoException;
import tratamento.ColaboradorException;
import tratamento.ComercioException;
import tratamento.EstabelecimentoException;
import tratamento.LoginException;
import tratamento.UsuarioException;

public class Interface {
	private int menuInicial, menuPrincipal;
	private Scanner sc;
	
	SistemaGestao erpEmpresa;
	Usuario usuarioErp;
	Colaborador colaboradorEmpresa;
	Cartao cartaoRefeicao;
	GestaoCartao operadoraCartao;
	Consumo consumo;
	Fluxo fluxoJRango;
	
	Comercio comercio;
	Produto p1, p2, p3;
	Estabelecimento restaurante, churrascaria, pizzaria, papelaria;
	
	public Interface() {
		erpEmpresa = new SistemaGestao();
		operadoraCartao = new GestaoCartao();
		comercio = new Comercio();
		sc = new Scanner(System.in);
				
		// Restaurante #1
		restaurante = new Restaurante("Luigi Restaurante");
		comercio.cadastrarEstabelecimento(restaurante);
		p1 = new Produto("Prato Feito", 18);
		p2 = new Produto("Buffet Livre", 25);
		p3 = new Produto("Refrigerante", 5);
		restaurante.cadastrarProduto(p1);
		restaurante.cadastrarProduto(p2);
		restaurante.cadastrarProduto(p3);
		
		// Restaurante #2
		restaurante = new Restaurante("Mineirinho Restaurante");
		comercio.cadastrarEstabelecimento(restaurante);
		p1 = new Produto("Prato Feito", 22);
		p2 = new Produto("Buffet Livre", 28);
		p3 = new Produto("Refrigerante", 5);
		restaurante.cadastrarProduto(p1);
		restaurante.cadastrarProduto(p2);
		restaurante.cadastrarProduto(p3);
		
		// Churrascaria
		churrascaria = new Churrascaria("Los Pampas");
		comercio.cadastrarEstabelecimento(churrascaria);
		p1 = new Produto("Rod�zio de Carnes", 29);
		p2 = new Produto("Buffet Livre", 49);
		p3 = new Produto("Refrigerante Lata", 5);
		churrascaria.cadastrarProduto(p1);
		churrascaria.cadastrarProduto(p2);
		churrascaria.cadastrarProduto(p3);
		
		//Pizzaria
		pizzaria = new Pizzaria("Top Pizza");
		comercio.cadastrarEstabelecimento(pizzaria);
		p1 = new Produto("Pizza gigante", 50);
		p2 = new Produto("Pizza grande", 39);
		p3 = new Produto("Refrigerante 2L", 10);
		pizzaria.cadastrarProduto(p1);
		pizzaria.cadastrarProduto(p2);
		pizzaria.cadastrarProduto(p3);
		
		//Papelaria
		papelaria = new Papelaria("Nobre Papelaria");
		comercio.cadastrarEstabelecimento(papelaria);
		p1 = new Produto("Caderno", 10);
		p2 = new Produto("Caneta", 2);
		p3 = new Produto("L�pis", 1);
		papelaria.cadastrarProduto(p1);
		papelaria.cadastrarProduto(p2);
		papelaria.cadastrarProduto(p3);
	}
	
	public void menuInicial() {
		while(menuInicial == 0) {
			String leituraTeclado;
			
			String usuario = null;
			String senha = null;
			
			int escolhaMenuInicial = 0;
			System.out.println("## JRango 2.0 - Menu Inicial ##");
			System.out.println("1) Logar no sistema");
			System.out.println("2) Cadastrar novo usu�rio");
			System.out.println("3) Sair");
			lerDadosFluxo();
			System.out.print("Digita uma op��o: ");
			try{
				leituraTeclado = sc.nextLine();
				escolhaMenuInicial = Integer.parseInt(leituraTeclado);
			} catch(NumberFormatException e){
				System.out.println("-> ATEN��O: Digite um n�mero correspondente ao menu!");
			}
			
			switch(escolhaMenuInicial) {
			case 1:
				if(erpEmpresa.verificarExistenciaUsuarios() == true) {
					
					sc = new Scanner(System.in);
					System.out.print("- Usu�rio: ");
					usuario = sc.nextLine();
					System.out.print("- Senha: ");
					senha = sc.nextLine();
					try {
						if(erpEmpresa.validarLogin(usuario, senha) == true) {
							menuPrincipal(usuario);
							break;
						} 
					}catch(LoginException e) {
						System.out.println(e.getErro());
						break;
					}
				} else {
					System.out.println("-> ATEN��O: N�o h� usu�rios cadastrados para acessar o sistema, cadastre um usu�rio para acessar!");
					break;
				}
			case 2:

				int nivelAcesso = 0;
				boolean admin = false;
				
				sc = new Scanner(System.in);
				System.out.print("- Crie um usu�rio (Ex: john.doe): ");
				usuario = sc.nextLine();
				System.out.print("- Crie uma senha para este usu�rio: ");
				senha = sc.nextLine();
				System.out.print("- Esse usu�rio ser� um Administrador?\n Digite '1' para Sim e '0' para N�o: ");
				leituraTeclado = sc.nextLine();
				
				try {
					nivelAcesso = Integer.parseInt(leituraTeclado);
					if(nivelAcesso != 0) {
						admin = true;
					}
					usuarioErp = new Usuario(usuario, senha, admin);
					if(erpEmpresa.verificarExistenciaUsuarios(usuario) != true) {
						if(erpEmpresa.cadastrarUsuario(usuarioErp) == true) {
							System.out.println("-> Cadastrado com sucesso!");
							break;
						}
					} else {
						System.out.println("-> ATEN��O: Usu�rio com esse login j� cadastrado!");
						break;
					}
				}catch(NumberFormatException e) {
					System.out.println("-> ATEN��O: Digite apenas n�meros, para definir se o usu�rio ter� perfil de Administrador!");
					break;
				}catch(UsuarioException e) {
					System.out.println(e.getErro());
					break;
				}catch(NullPointerException e) {
					e.printStackTrace();
					break;
				}
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("-> ATEN��O: Op��o errada!");
				break;
			}
		}
	}
	
	private void menuPrincipal(String usuario) {
		while(menuPrincipal == 0) {
			String leituraTeclado;
			int escolhaMenuPrincipal = 0;
			
			System.out.println("## JRango 2.0 - Menu Principal - Usu�rio, " + usuario + " ##");
			System.out.println("1) Cadastrar um colaborador");
			System.out.println("2) Cadastrar um cart�o");
			System.out.println("3) Carregar cr�ditos");
			System.out.println("4) Consumir cr�ditos");
			System.out.println("5) Salvar os dados em um arquivo (fluxos)");
			System.out.println("6) Visualizar relat�rios");
			System.out.println("7) Efetuar logoff");
			System.out.print("- Digite uma op��o: ");
			try{
				leituraTeclado = sc.nextLine();
				escolhaMenuPrincipal = Integer.parseInt(leituraTeclado);
			} catch(NumberFormatException e){
				System.out.println("-> ATEN��O: Digite um n�mero correspondente ao menu!");
			}
			
			switch(escolhaMenuPrincipal) {
			case 1:
				if(erpEmpresa.verificarAdministrador(usuario) == true) {
					System.out.print("- Digite o nome completo do colaborador: ");
					try {
						leituraTeclado = sc.nextLine();
						colaboradorEmpresa = new Colaborador(leituraTeclado);
						if(erpEmpresa.cadastrarColaborador(colaboradorEmpresa) == true) {
							System.out.println("-> Colaborador cadastrado com sucesso!");
						}
						break;
					}catch(ColaboradorException e) {
						System.out.println(e.getErro());
						break;
					}catch(NullPointerException e) {
						e.printStackTrace();
						break;
					}
				} else {
					System.out.println("-> ATEN��O: Somente usu�rios com permiss�o administrativa podem cadastrar um colaborador.");
					break;
				}
			case 2:
				if(erpEmpresa.verificarAdministrador(usuario) == true) {
					if(erpEmpresa.verificarExistenciaColaboradores() == true) {
						int idColaborador;
						
						System.out.println("Vamos selecionar um colaborador, para associa-lo ao cart�o refei��o: ");
						System.out.println("-------------------------------");
						erpEmpresa.listarColaboradores();
						System.out.println("-------------------------------");
						System.out.print("- Selecione um colaborador: ");
						leituraTeclado = sc.nextLine();
						try {
							idColaborador = Integer.parseInt(leituraTeclado);
							if(erpEmpresa.verificarVRColaborador(idColaborador) == false) {
								cartaoRefeicao = new Cartao(erpEmpresa.retornaColaborador(idColaborador), operadoraCartao.gerarNumCartao(), operadoraCartao.gerarSenha());
								if(operadoraCartao.cadastrarCartao(cartaoRefeicao) == true) {
									erpEmpresa.confirmarVRColaborador(idColaborador);
									System.out.println("-------------------------------");
									System.out.println("-> Cart�o cadastrado e associado ao colaborador com sucesso!");
									System.out.println("-> Numero do cart�o: " + cartaoRefeicao.getNumCartao()+ ", Senha tempor�ria: " + cartaoRefeicao.getSenhaCartao());
									System.out.println("-------------------------------");
									break;
								}
							} else {
								System.out.println("Colaborador j� possu� cart�o refei��o!");
								break;
							}
						}catch(NumberFormatException e) {
							System.out.println("-> ATEN��O: Somente n�meros! Selecione um n�mero correspondente ao colaborador na lista.");
							break;
						}catch(ColaboradorException e) {
							System.out.println(e.getErro());
							break;
						}catch(NullPointerException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("N�o h� colaboradores cadastrados!\nCadastre um colaborador para associa-lo � um cart�o refei��o.");
						break;
					}
				} else {
					System.out.println("Somente usu�rios com permiss�o administrativa podem cadastrar um cart�o!");
					break;
				}
			case 3:
				if(erpEmpresa.verificarAdministrador(usuario) == true) {
					if(operadoraCartao.verificarExistenciaCartoes() == true) {
						int idCartao, valorRecarga = 0;
						
						System.out.println("Vamos selecionar o cart�o que deseja recarregar:");
						System.out.println("-------------------------------");
						operadoraCartao.listarCartoes();
						System.out.println("-------------------------------");
						System.out.print("- Selecione um cart�o: ");
						leituraTeclado = sc.nextLine();
						try {
							idCartao = Integer.parseInt(leituraTeclado);
							
							System.out.print("- Digite o valor da recarga: R$");
							leituraTeclado = sc.nextLine();
							valorRecarga = Integer.parseInt(leituraTeclado);
														
							if(operadoraCartao.carregarCreditos(operadoraCartao.retornarNumCartao(idCartao), valorRecarga) == true) {
								System.out.println("-> Cr�ditos carregados com sucesso!");
								break;
							}
						}catch(NumberFormatException e) {
							System.out.println("-> ATEN��O: Somente n�meros! Digite um n�mero correspondente � um cart�o.");
							break;
						}catch(CartaoException e) {
							System.out.println(e.getErro());
							break;
						}catch(NullPointerException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("N�o h� cart�es cadastrados para recarregar cr�ditos!");
						break;
					}
				} else {
					System.out.println("Somente usu�rios com permiss�o administrativa podem recarregar cr�ditos!");
					break;
				}
			case 4:
				menuConsumirCreditos();
				break;
			case 5:
				System.out.println("Salvando todo o fluxo em um �nico arquivo...");
				
				fluxoJRango = new Fluxo(erpEmpresa.retornarListaUsuarios(), erpEmpresa.retornarListaColaboradores(), operadoraCartao.retornarListaCartoes(), operadoraCartao.retornarListaConsumos(), comercio.retornarListaEstabelecimentos());
				ObjectOutputStream out = null;
				try {
					out = new ObjectOutputStream(new FileOutputStream("c:\\temp\\fluxoJRango.dad"));
					out.writeObject(fluxoJRango);
					System.out.println("-> Fluxo armazenado com sucesso!");
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}finally {
					try {
						if(out != null) {
							out.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;
			case 6:
				menuRelatorio();
				break;
			case 7:
				menuInicial();
				break;
			default:
				System.out.println("Op��o errada!");
				break;
			}
		}
	}
	
	public void menuConsumirCreditos() {
		String leituraTeclado;
		int menuConsumir = 0, escolhaMenuConsumir = 0;
		int idEstabelecimento, idProduto, valorConsumo = 0, numCartao, senhaCartao;
		
		sc = new Scanner(System.in);
		System.out.println("Vamos selecionar um estabelecimento abaixo: ");
		System.out.println("-------------------------------");
		comercio.listarEstabelecimentos();
		System.out.println("-------------------------------");
		System.out.print("- Selecione um estabelecimento: ");
		leituraTeclado = sc.nextLine();
		
		try {
			idEstabelecimento = Integer.parseInt(leituraTeclado);
			
			while(menuConsumir == 0) {
				System.out.println("## Seja bem vindo ao " + comercio.retornarEstabelecimento(idEstabelecimento).getNome() + ", escolha uma das op��es ##");
				System.out.println("1) Listar produtos para consumo");
				System.out.println("2) Realizar pagamento c/ Cart�o Refei��o");
				System.out.println("3) Realizar pagamento em dinheiro");
				System.out.println("Total a pagar: R$" + valorConsumo);
				System.out.print("- Digite uma op��o: ");
				leituraTeclado = sc.nextLine();
				escolhaMenuConsumir = Integer.parseInt(leituraTeclado);
				
				try {
					switch(escolhaMenuConsumir) {
					case 1:
						System.out.println("Selecione um dos produtos abaixo para consumir");
						System.out.println("-------------------------------");
						comercio.retornarEstabelecimento(idEstabelecimento).listarProdutos();
						System.out.println("-------------------------------");
						System.out.print("- Selecione um produto para consumir: ");
						leituraTeclado = sc.nextLine();
						idProduto = Integer.parseInt(leituraTeclado);
						valorConsumo = valorConsumo + comercio.retornarEstabelecimento(idEstabelecimento).retornaPrecoProduto(idProduto);
						break;
					case 2:
						LocalDate data = LocalDate.now();
						String hoje = data.toString();
						
						int operacao;
						System.out.print("- Digite o numero do seu cart�o: ");
						leituraTeclado = sc.nextLine();
						numCartao = Integer.parseInt(leituraTeclado);
						
						System.out.print("- Digite sua senha: ");
						leituraTeclado = sc.nextLine();
						senhaCartao = Integer.parseInt(leituraTeclado);
						
						if(comercio.retornarEstabelecimento(idEstabelecimento).getRamo() == "aliminticio") {
							operacao = operadoraCartao.realizarPagamento(numCartao, senhaCartao, valorConsumo);
							if(operacao > 0) {
								consumo = new Consumo(numCartao, hoje, comercio.retornarEstabelecimento(idEstabelecimento), valorConsumo);
								operadoraCartao.registrarConsumo(consumo);
								System.out.println("Pagamento efetuado com sucesso!");
								System.out.println("-> Imprimindo seu comprovante... Creditos atuais R$" + operacao);
								System.out.println("-> Obrigado, volte sempre!");
								menuConsumir = -1;
								break;
							}
						} else {
							System.out.println("Transa��o n�o foi realizada!");
							System.out.println("-> O estabelecimento n�o � do ramo al�menticio!");
							break;
						}
						
					case 3:
						System.out.println("-> Pagamento em dinheiro...");
						System.out.println("-> Obrigado, volte sempre!");
						menuConsumir = -1;
						break;
					default:
						System.out.println("Op��o errada!");
						break;
					}
				}catch(NumberFormatException e) {
					System.out.println("-> ATEN��O: Somente n�meros!");
				}catch(ComercioException e) {
					System.out.println(e.getErro());
				}catch(EstabelecimentoException e) {
					System.out.println(e.getErro());
				}catch(CartaoException e) {
					System.out.println(e.getErro());
				}
			}
		}catch(NumberFormatException e) {
			System.out.println("-> ATEN��O: Somente n�meros!");
		}catch(ComercioException e) {
			System.out.println(e.getErro());
		}
	}
	
	private void menuRelatorio() {
		String leituraTeclado;
		int menuRelatorio = 0, escolhaMenuRelatorio = 0, numCartao;
		
		try {
			while(menuRelatorio == 0) {
				System.out.println("Selecione uma das op��es de relat�rio: ");
				System.out.println("1) Cr�ditos dispon�veis");
				System.out.println("2) �ltimos consumos");
				System.out.println("3) Restaurantes mais frequentados");
				System.out.println("4) Voltar Menu Principal");
				System.out.print("- Digite uma op��o: ");
				leituraTeclado = sc.nextLine();
				escolhaMenuRelatorio = Integer.parseInt(leituraTeclado);
				
				switch(escolhaMenuRelatorio) {
				case 1:
					System.out.print("- Digite o n�mero do cart�o: ");
					leituraTeclado = sc.nextLine();
					numCartao = Integer.parseInt(leituraTeclado);
					System.out.println("Cr�ditos restantes, R$" + operadoraCartao.consultarCreditos(numCartao));
					break;
				case 2:
					System.out.print("- Digite o n�mero do cart�o: ");
					leituraTeclado = sc.nextLine();
					numCartao = Integer.parseInt(leituraTeclado);
					if(operadoraCartao.verificarExistenciaConsumos() == true) {
						operadoraCartao.listarConsumo(numCartao);
					} else {
						System.out.println("-> ATEN��O: N�o h� consumos registrados ainda!");
					}
					break;
				case 3:
					operadoraCartao.restaurantesMaisFrequentados();					
					break;
				case 4:
					menuRelatorio = -1;
					break;
				default:
					System.out.println("Op��o errada!");
					break;
				}
			}
		}catch(NumberFormatException e) {
			System.out.println("-> ATEN��O: Somente n�meros!");
		}
	}
	
	private void lerDadosFluxo() {
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream("c:\\temp\\fluxoJRango.dad"));
			fluxoJRango = (Fluxo)in.readObject();
			erpEmpresa.carregarListaColaboradores(fluxoJRango.getColaboradores());
			erpEmpresa.carregarListaUsuarios(fluxoJRango.getUsuarios());
			operadoraCartao.carregarListaCartoes(fluxoJRango.getCartoes());
			operadoraCartao.carregarListaConsumos(fluxoJRango.getConsumos());
			comercio.carregarListaEstabelecimentos(fluxoJRango.getEstabelecimentos());
			System.out.println("-> Fluxo carregado sucesso!");
		}catch(FileNotFoundException e) {
			System.out.println("-> Nenhum fluxo foi localizado.");
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}