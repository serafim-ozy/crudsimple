@extends('layouts.app')

@section('title', 'Editar Veículo')

@section('content')
  <h1>Editar Veículo</h1>

  @if($errors->any())
    <div class="alert alert-danger">
      <ul class="mb-0">
        @foreach($errors->all() as $error)
          <li>{{ $error }}</li>
        @endforeach
      </ul>
    </div>
  @endif

  <form action="{{ route('veiculos.update', $veiculo) }}" method="POST">
    @csrf
    @method('PUT')

    <div class="mb-3">
      <label class="form-label">Marca</label>
      <input type="text" name="marca" value="{{ old('marca', $veiculo->marca) }}" class="form-control" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Modelo</label>
      <input type="text" name="modelo" value="{{ old('modelo', $veiculo->modelo) }}" class="form-control" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Ano de Fabricação</label>
      <input type="number" name="ano_fabricacao" value="{{ old('ano_fabricacao', $veiculo->ano_fabricacao) }}" class="form-control" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Placa (AAA-1234)</label>
      <input type="text" name="placa" value="{{ old('placa', $veiculo->placa) }}" class="form-control" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Preço</label>
      <input type="text" name="preco" value="{{ old('preco', $veiculo->preco) }}" class="form-control" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Cor</label>
      <input type="text" name="cor" value="{{ old('cor', $veiculo->cor) }}" class="form-control" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Descrição</label>
      <textarea name="descricao" class="form-control">{{ old('descricao', $veiculo->descricao) }}</textarea>
    </div>

    <button class="btn btn-primary">Atualizar</button>
    <a href="{{ route('veiculos.index') }}" class="btn btn-secondary">Cancelar</a>
  </form>

@endsection
