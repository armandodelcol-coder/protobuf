package br.com.zupedu.armando

import io.grpc.ManagedChannelBuilder

fun main() {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50001)
        .usePlaintext()
        .build()

    val request = FuncionarioRequest.newBuilder()
        .setNome("Armando Tadeu")
        .setCpf("44444444444")
        .setSalario(2000.20)
        .setIdade(26)
        .setAtivo(true)
        .setCargo(Cargo.DEV) // cargo eh o 0 do enum, default não serializa para economizar bytes
        .addEnderecos(
            FuncionarioRequest.Endereco.newBuilder()
                .setLogradouro("Rua Fake")
                .setCep("00000000")
                .setComplemento("Nnehum")
                .build()
        ).build()

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)
    val response = client.send(request)

    println(response)
}