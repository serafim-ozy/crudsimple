<?php

namespace Database\Seeders;

use App\Models\User;
use App\Models\Computador;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    use WithoutModelEvents;
    public function run(): void
    {
        Computador::create([
            'marca' => 'Dell',
            'preco' => 2500.00,
            'ram' => 16,
            'disco' => 512
        ]);
        Computador::create([
            'marca' => 'HP',
            'preco' => 3000.00,
            'ram' => 32,
            'disco' => 1024
        ]);
    }
}
