	
	var homeapp = angular.module('homeApp',['ngRoute','ngMask']);
	
	homeapp.config(function($routeProvider){
		$routeProvider
		.when('/pessoa/:id_operador&:id_perfil',{
	    	resolve:{
	    		"check": function($location, $rootScope){
	    			if(!$rootScope.loggedIn){
	    				$location.path('/');
	    			}
	    		}
	    	},
			templateUrl:'pessoa.html',
			controller:'PessoasController'
			})
		.when('/',{
			templateUrl:'login.html',
			controller:'loginController'
			})
	    .when('/operador',{
	    	resolve:{
	    		"check": function($location, $rootScope){
	    			if(!$rootScope.loggedIn){
	    				$location.path('/');
	    			}
	    		}
	    	},
	    	templateUrl:'operador.html',
			controller:'OperadoresController'
			})
			.otherwise({
			redirectTo:'/'
		});
	});
	
	homeapp.controller('loginController',function($scope,loginService,$location,$rootScope){
				
		$scope.submit = function(login){
			loginService.login(login).then(function(resposta){
				$scope.operador = (resposta.data);
				if($scope.operador.senha != $scope.senha){
					alert('Os dados do login estao errados. Por favor verifique !')
				}else{
				if($scope.operador.id_perfil === 1){
					$rootScope.loggedIn = true;
					$location.path('/operador/');
				}else{
					$rootScope.loggedIn = true;
					$location.path('/pessoa/'+$scope.operador.id_operador+'&'+$scope.operador.id_perfil);
				}
			}
				
			});
		};
	});
	
	homeapp.controller('OperadoresController',function($scope,OperadoresService){
		
		$scope.operador = {};
		$scope.isEdit = 0;
		
		listar();
		listarPerfis();
		
		function listar(){
			OperadoresService.listar().then(function(resposta){
				$scope.operadores = resposta.data;
			});
		}
		
		function listarPerfis(){
			OperadoresService.listarPerfis().then(function(resposta){
				$scope.perfis = resposta.data;
			});
		}
		
		$scope.salvar = function(operador){
			OperadoresService.salvar(operador).then(listar);
			$scope.operador = {};
			$scope.isEdit = 0;
		};
		
		$scope.editar = function(operador){
			$scope.operador = angular.copy(operador);
			$scope.isEdit = 1;
		};
		
		$scope.excluir = function(operador){
			OperadoresService.excluir(operador).then(listar);
		};
		
		$scope.cancelar = function(){
			$scope.operador = {};
			$scope.isEdit = 0;
		};
	});
	
	homeapp.controller('PessoasController',function($scope,PessoasService,$routeParams){
		
		$scope.operador = {};
		$scope.pessoa = {};
		$scope.isEdit = 0;
		
		var cod_operador = $routeParams.id_operador;
		var operador_id_perfil = $routeParams.id_perfil;
		
		$scope.pessoa.cod_operador = cod_operador;
		$scope.operador.id_perfil = operador_id_perfil;
		
		listar();
		
		function listar(){
			PessoasService.listar().then(function(resposta){
				$scope.pessoas = resposta.data;
			});
		}	
		
	function listarTelefones(pessoa){
		PessoasService.listarTelefones(pessoa).then(function(resposta){
			$scope.telefones = resposta.data;
		});
	}
	
	$scope.listarTelefones = function(pessoa){
		listarTelefones(pessoa);
		}
		
		
/*		$scope.listarTelefones = function(pessoa){
			PessoasService.listarTelefones(pessoa).then(function(resposta){
				$scope.telefones = resposta.data;
			});
		}*/
		
		$scope.salvar = function(pessoa){
			PessoasService.salvar(pessoa).then(listar);
			$scope.pessoa = {};
			$scope.pessoa.cod_operador = cod_operador;
			$scope.operador.id_perfil = operador_id_perfil;
			$scope.isEdit = 0;
		};
		
		$scope.editar = function(pessoa){
			$scope.pessoa = angular.copy(pessoa);
			$scope.isEdit = 1;
		};
		
		$scope.excluir = function(pessoa){
			PessoasService.excluir(pessoa).then(listar);
		};
		
		$scope.excluirTelefone = function(telefone){
			$scope.pessoa.id_pessoa = telefone.id_pessoa;
			PessoasService.excluirTelefone(telefone).then(listarTelefones($scope.pessoa));
		}
		
		$scope.cancelar = function(){
			$scope.pessoa = {};
			$scope.pessoa.cod_operador = cod_operador;
			$scope.operador.id_perfil = operador_id_perfil;
			$scope.isEdit = 0;
		};
	});
	homeapp.service('loginService',function($http){
		var api = 'http://localhost:8080/api/webresources/login';
		
		this.login = function(login){
			return $http.get(api + '/' + login);
		};
		
	});
	
	
	homeapp.service('OperadoresService', function($http){
		var api = 'http://localhost:8080/api/webresources/operadores';
		var perfis = 'http://localhost:8080/api/webresources/perfis';

		
		this.listar = function(){
			return $http.get(api);
		};
		
		this.listarPerfis = function(){
			return $http.get(perfis);
		};
		
		this.salvar = function(operador){
			if(operador.id_operador){
				return $http.put(api + '/' + operador.id_operador,operador);
			}else{
				return $http.post(api,operador);
			}
		};
		
		this.excluir = function(operador){
			return $http.delete(api + '/' + operador.id_operador);
		};
	});
	
	homeapp.service('PessoasService',function($http){
		var api = 'http://localhost:8080/api/webresources/pessoas';
		var apiTelefones = 'http://localhost:8080/api/webresources/telefones';
		
		this.listarTelefones = function(pessoa){
			return $http.get(apiTelefones+'/pessoa/'+pessoa.id_pessoa);
		}
		
		this.listar = function(){
			return $http.get(api);
		};
		
		this.salvar = function(pessoa){
			if(pessoa.id_pessoa){
				return $http.put(api + '/' + pessoa.id_pessoa,pessoa);
			}else{
				return $http.post(api,pessoa);
			}
		};
		
		this.excluir = function(pessoa){
			return $http.delete(api + '/' + pessoa.id_pessoa);
		};
		
		this.excluirTelefone = function(telefone){
			return $http.delete(apiTelefones + '/' + telefone.id_telefone);
		}
	});