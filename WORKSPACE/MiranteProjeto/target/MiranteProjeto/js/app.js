	
	var app = angular.module('operadoresApp',[]);
	
	app.controller('OperadoresController',function($scope,OperadoresService){
		
		$scope.operador = {};
		
		listar();
		
		function listar(){
			OperadoresService.listar().then(function(resposta){
				$scope.operadores = resposta.data;
			});
		}
		
		$scope.salvar = function(operador){
			OperadoresService.salvar(operador).then(listar);
			$scope.operador = {};
		};
		
		$scope.editar = function(operador){
			$scope.operador = angular.copy(operador);
		};
		
		$scope.excluir = function(operador){
			OperadoresService.excluir(operador).then(listar);
		};
		
		$scope.cancelar = function(){
			$scope.operador = {};
		};
	});
	
	app.service('OperadoresService', function($http){
		var api = 'http://localhost:8080/api/webresources/operadores';
		
		this.listar = function(){
			return $http.get(api);
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