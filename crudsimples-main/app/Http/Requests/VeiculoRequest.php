<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Rule;

class VeiculoRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        $veiculo = $this->route('veiculo');

        $placaRule = Rule::unique('veiculos', 'placa');
        if ($veiculo) {
            $placaRule = $placaRule->ignore($veiculo->id);
        }

        return [
            'marca' => ['required', 'string', 'max:255'],
            'modelo' => ['required', 'string', 'max:255'],
            'ano_fabricacao' => ['required', 'integer', 'min:1'],
            'placa' => ['required', 'string', 'max:20', $placaRule, 'regex:/^[A-Z]{3}-[0-9]{4}$/'],
            'preco' => ['required', 'numeric', 'min:0.01'],
            'cor' => ['required', 'string', 'max:50'],
            'descricao' => ['nullable', 'string'],
        ];
    }

    public function messages()
    {
        return [
            'placa.regex' => 'O formato da placa é inválido. Use AAA-1234.',
        ];
    }
}
