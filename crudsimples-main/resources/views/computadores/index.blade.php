<html>
<head>
    <title>Computadores</title> 
</head>
<body>
    <h1>Computadores</h1>
    <a href="/computadores/create">Criar novo computador</a>
    <ul>
        @foreach ($computadores as $computador)
            <li>{{ $computador->marca }} 
                - 
                {{ $computador->preco }}
                -
                {{ $computador->ram }}
                -
                {{ $computador->disco }}
            </li>
        @endforeach
    </ul>   
</body>
</html>