@extends('layouts.app')

@section('title', 'Detalhes do Veículo')

@section('content')
  <h1>Veículo #{{ $veiculo->id }}</h1>

  <div class="mb-3"><strong>Marca:</strong> {{ $veiculo->marca }}</div>
  <div class="mb-3"><strong>Modelo:</strong> {{ $veiculo->modelo }}</div>
  <div class="mb-3"><strong>Ano de Fabricação:</strong> {{ $veiculo->ano_fabricacao }}</div>
  <div class="mb-3"><strong>Placa:</strong> {{ $veiculo->placa }}</div>
  <div class="mb-3"><strong>Preço:</strong> R$ {{ number_format($veiculo->preco, 2, ',', '.') }}</div>
  <div class="mb-3"><strong>Cor:</strong> {{ $veiculo->cor }}</div>
  <div class="mb-3"><strong>Descrição:</strong> {{ $veiculo->descricao ?? '-' }}</div>

  <a href="{{ route('veiculos.edit', $veiculo) }}" class="btn btn-warning">Editar</a>
  <a href="{{ route('veiculos.index') }}" class="btn btn-secondary">Voltar</a>

@endsection
