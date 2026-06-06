@extends('layouts.app')

@section('title', 'Lista de Veículos')

@section('content')
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h1>Veículos</h1>
    <a href="{{ route('veiculos.create') }}" class="btn btn-primary">Novo Veículo</a>
  </div>

  <table class="table table-striped">
    <thead>
      <tr>
        <th>#</th>
        <th>Marca</th>
        <th>Modelo</th>
        <th>Ano</th>
        <th>Placa</th>
        <th>Preço</th>
        <th>Cor</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      @foreach($veiculos as $veiculo)
        <tr>
          <td>{{ $veiculo->id }}</td>
          <td>{{ $veiculo->marca }}</td>
          <td>{{ $veiculo->modelo }}</td>
          <td>{{ $veiculo->ano_fabricacao }}</td>
          <td>{{ $veiculo->placa }}</td>
          <td>R$ {{ number_format($veiculo->preco, 2, ',', '.') }}</td>
          <td>{{ $veiculo->cor }}</td>
          <td>
            <a href="{{ route('veiculos.show', $veiculo) }}" class="btn btn-sm btn-info">Ver</a>
            <a href="{{ route('veiculos.edit', $veiculo) }}" class="btn btn-sm btn-warning">Editar</a>
            <form action="{{ route('veiculos.destroy', $veiculo) }}" method="POST" style="display:inline-block" onsubmit="return confirm('Remover este veículo?')">
              @csrf
              @method('DELETE')
              <button class="btn btn-sm btn-danger">Excluir</button>
            </form>
          </td>
        </tr>
      @endforeach
    </tbody>
  </table>

  {{ $veiculos->links() }}

@endsection
