<?php

use App\Http\Controllers\ComputadorController;
use App\Http\Controllers\VeiculoController;
use Illuminate\Support\Facades\Route;

Route::view('/', 'welcome');
Route::get('computador', [ComputadorController::class, 'index']);
Route::resource('veiculos', VeiculoController::class);