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
		p1 = new Produto("Rodízio de Carnes", 29);
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
		p3 = new Produto("Lápis", 1);
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
			System.out.println("2) Cadastrar novo usuário");
			System.out.println("3) Sair");
			lerDadosFluxo();
			System.out.print("Digita uma opção: ");
			try{
				leituraTeclado = sc.nextLine();
				escolhaMenuInicial = Integer.parseInt(leituraTeclado);
			} catch(NumberFormatException e){
				System.out.println("-> ATENÇÃO: Digite um número correspondente ao menu!");
			}
			
			switch(escolhaMenuInicial) {
			case 1:
				if(erpEmpresa.verificarExistenciaUsuarios() == true) {
					
					sc = new Scanner(System.in);
					System.out.print("- Usuário: ");
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
					System.out.println("-> ATENÇÃO: Não há usuários cadastrados para acessar o sistema, cadastre um usuário para acessar!");
					break;
				}
			case 2:

				int nivelAcesso = 0;
				boolean admin = false;
				
				sc = new Scanner(System.in);
				System.out.print("- Crie um usuário (Ex: john.doe): ");
				usuario = sc.nextLine();
				System.out.print("- Crie uma senha para este usuário: ");
				senha = sc.nextLine();
				System.out.print("- Esse usuário será um Administrador?\n Digite '1' para Sim e '0' para Não: ");
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
						System.out.println("-> ATENÇÃO: Usuário com esse login já cadastrado!");
						break;
					}
				}catch(NumberFormatException e) {
					System.out.println("-> ATENÇÃO: Digite apenas números, para definir se o usuário terá perfil de Administrador!");
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
				System.out.println("-> ATENÇÃO: Opção errada!");
				break;
			}
		}
	}
	
	private void menuPrincipal(String usuario) {
		while(menuPrincipal == 0) {
			String leituraTeclado;
			int escolhaMenuPrincipal = 0;
			
			System.out.println("## JRango 2.0 - Menu Principal - Usuário, " + usuario + " ##");
			System.out.println("1) Cadastrar um colaborador");
			System.out.println("2) Cadastrar um cartão");
			System.out.println("3) Carregar créditos");
			System.out.println("4) Consumir créditos");
			System.out.println("5) Salvar os dados em um arquivo (fluxos)");
			System.out.println("6) Visualizar relatórios");
			System.out.println("7) Efetuar logoff");
			System.out.print("- Digite uma opção: ");
			try{
				leituraTeclado = sc.nextLine();
				escolhaMenuPrincipal = Integer.parseInt(leituraTeclado);
			} catch(NumberFormatException e){
				System.out.println("-> ATENÇÃO: Digite um número correspondente ao menu!");
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
					System.out.println("-> ATENÇÃO: Somente usuários com permissão administrativa podem cadastrar um colaborador.");
					break;
				}
			case 2:
				if(erpEmpresa.verificarAdministrador(usuario) == true) {
					if(erpEmpresa.verificarExistenciaColaboradores() == true) {
						int idColaborador;
						
						System.out.println("Vamos selecionar um colaborador, para associa-lo ao cartão refeição: ");
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
									System.out.println("-> Cartão cadastrado e associado ao colaborador com sucesso!");
									System.out.println("-> Numero do cartão: " + cartaoRefeicao.getNumCartao()+ ", Senha temporária: " + cartaoRefeicao.getSenhaCartao());
									System.out.println("-------------------------------");
									break;
								}
							} else {
								System.out.println("Colaborador já possuí cartão refeição!");
								break;
							}
						}catch(NumberFormatException e) {
							System.out.println("-> ATENÇÃO: Somente números! Selecione um número correspondente ao colaborador na lista.");
							break;
						}catch(ColaboradorException e) {
							System.out.println(e.getErro());
							break;
						}catch(NullPointerException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("Não há colaboradores cadastrados!\nCadastre um colaborador para associa-lo à um cartão refeição.");
						break;
					}
				} else {
					System.out.println("Somente usuários com permissão administrativa podem cadastrar um cartão!");
					break;
				}
			case 3:
				if(erpEmpresa.verificarAdministrador(usuario) == true) {
					if(operadoraCartao.verificarExistenciaCartoes() == true) {
						int idCartao, valorRecarga = 0;
						
						System.out.println("Vamos selecionar o cartão que deseja recarregar:");
						System.out.println("-------------------------------");
						operadoraCartao.listarCartoes();
						System.out.println("-------------------------------");
						System.out.print("- Selecione um cartão: ");
						leituraTeclado = sc.nextLine();
						try {
							idCartao = Integer.parseInt(leituraTeclado);
							
							System.out.print("- Digite o valor da recarga: R$");
							leituraTeclado = sc.nextLine();
							valorRecarga = Integer.parseInt(leituraTeclado);
														
							if(operadoraCartao.carregarCreditos(operadoraCartao.retornarNumCartao(idCartao), valorRecarga) == true) {
								System.out.println("-> Créditos carregados com sucesso!");
								break;
							}
						}catch(NumberFormatException e) {
							System.out.println("-> ATENÇÃO: Somente números! Digite um número correspondente à um cartão.");
							break;
						}catch(CartaoException e) {
							System.out.println(e.getErro());
							break;
						}catch(NullPointerException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("Não há cartões cadastrados para recarregar créditos!");
						break;
					}
				} else {
					System.out.println("Somente usuários com permissão administrativa podem recarregar créditos!");
					break;
				}
			case 4:
				menuConsumirCreditos();
				break;
			case 5:
				System.out.println("Salvando todo o fluxo em um único arquivo...");
				
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
				System.out.println("Opção errada!");
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
				System.out.println("## Seja bem vindo ao " + comercio.retornarEstabelecimento(idEstabelecimento).getNome() + ", escolha uma das opções ##");
				System.out.println("1) Listar produtos para consumo");
				System.out.println("2) Realizar pagamento c/ Cartão Refeição");
				System.out.println("3) Realizar pagamento em dinheiro");
				System.out.println("Total a pagar: R$" + valorConsumo);
				System.out.print("- Digite uma opção: ");
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
						System.out.print("- Digite o numero do seu cartão: ");
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
							System.out.println("Transação não foi realizada!");
							System.out.println("-> O estabelecimento não é do ramo alímenticio!");
							break;
						}
						
					case 3:
						System.out.println("-> Pagamento em dinheiro...");
						System.out.println("-> Obrigado, volte sempre!");
						menuConsumir = -1;
						break;
					default:
						System.out.println("Opção errada!");
						break;
					}
				}catch(NumberFormatException e) {
					System.out.println("-> ATENÇÃO: Somente números!");
				}catch(ComercioException e) {
					System.out.println(e.getErro());
				}catch(EstabelecimentoException e) {
					System.out.println(e.getErro());
				}catch(CartaoException e) {
					System.out.println(e.getErro());
				}
			}
		}catch(NumberFormatException e) {
			System.out.println("-> ATENÇÃO: Somente números!");
		}catch(ComercioException e) {
			System.out.println(e.getErro());
		}
	}
	
	private void menuRelatorio() {
		String leituraTeclado;
		int menuRelatorio = 0, escolhaMenuRelatorio = 0, numCartao;
		
		try {
			while(menuRelatorio == 0) {
				System.out.println("Selecione uma das opções de relatório: ");
				System.out.println("1) Créditos disponíveis");
				System.out.println("2) Últimos consumos");
				System.out.println("3) Restaurantes mais frequentados");
				System.out.println("4) Voltar Menu Principal");
				System.out.print("- Digite uma opção: ");
				leituraTeclado = sc.nextLine();
				escolhaMenuRelatorio = Integer.parseInt(leituraTeclado);
				
				switch(escolhaMenuRelatorio) {
				case 1:
					System.out.print("- Digite o número do cartão: ");
					leituraTeclado = sc.nextLine();
					numCartao = Integer.parseInt(leituraTeclado);
					System.out.println("Créditos restantes, R$" + operadoraCartao.consultarCreditos(numCartao));
					break;
				case 2:
					System.out.print("- Digite o número do cartão: ");
					leituraTeclado = sc.nextLine();
					numCartao = Integer.parseInt(leituraTeclado);
					if(operadoraCartao.verificarExistenciaConsumos() == true) {
						operadoraCartao.listarConsumo(numCartao);
					} else {
						System.out.println("-> ATENÇÃO: Não há consumos registrados ainda!");
					}
					break;
				case 3:
					operadoraCartao.restaurantesMaisFrequentados();					
					break;
				case 4:
					menuRelatorio = -1;
					break;
				default:
					System.out.println("Opção errada!");
					break;
				}
			}
		}catch(NumberFormatException e) {
			System.out.println("-> ATENÇÃO: Somente números!");
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