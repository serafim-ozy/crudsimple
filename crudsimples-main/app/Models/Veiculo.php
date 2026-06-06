<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Veiculo extends Model
{
    use HasFactory;

    protected $fillable = [
        'marca',
        'modelo',
        'ano_fabricacao',
        'placa',
        'preco',
        'cor',
        'descricao',
    ];
}
