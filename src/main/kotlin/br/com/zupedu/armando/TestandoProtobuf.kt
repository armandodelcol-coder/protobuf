package br.com.zupedu.armando

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
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

    println(request)
    // escrevendo o objeto em um arquivo binário
    request.writeTo(FileOutputStream("funcionario-request.bin"))

    // Lendo o arquivo binário
    val request2 = FuncionarioRequest.newBuilder()
        .mergeFrom(FileInputStream("funcionario-request.bin"))
    request2.cargo = Cargo.QA
    println(request2)

}