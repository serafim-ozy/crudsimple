<?php

namespace App\Http\Controllers;

use App\Http\Requests\VeiculoRequest;
use App\Models\Veiculo;
use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\View\View;

class VeiculoController extends Controller
{
    public function index(): View
    {
        $veiculos = Veiculo::orderBy('id', 'desc')->paginate(10);
        return view('veiculos.index', compact('veiculos'));
    }

    public function create(): View
    {
        return view('veiculos.create');
    }

    public function store(VeiculoRequest $request): RedirectResponse
    {
        Veiculo::create($request->validated());
        return redirect()->route('veiculos.index')->with('success', 'Veículo criado com sucesso.');
    }

    public function show(Veiculo $veiculo): View
    {
        return view('veiculos.show', compact('veiculo'));
    }

    public function edit(Veiculo $veiculo): View
    {
        return view('veiculos.edit', compact('veiculo'));
    }

    public function update(VeiculoRequest $request, Veiculo $veiculo): RedirectResponse
    {
        $veiculo->update($request->validated());
        return redirect()->route('veiculos.index')->with('success', 'Veículo atualizado com sucesso.');
    }

    public function destroy(Veiculo $veiculo): RedirectResponse
    {
        $veiculo->delete();
        return redirect()->route('veiculos.index')->with('success', 'Veículo removido com sucesso.');
    }
}
