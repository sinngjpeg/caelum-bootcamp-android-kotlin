package br.com.caelum.twittelumappweb.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.databinding.ActivityLoginBinding
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.viewmodel.UsuarioViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private val viewModel: UsuarioViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(UsuarioViewModel::class.java)
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginCriar.setOnClickListener { viewModel.cria(usuarioDaTela()) }
        binding.loginEntrar.setOnClickListener { viewModel.loga(usuarioDaTela()) }

    }

    private fun usuarioDaTela(): Usuario {
        val nome = binding.loginCampoNome.text.toString()
        val senha = binding.loginCampoSenha.text.toString()
        val username = binding.loginCampoUsername.text.toString()

        return Usuario(nome, senha, username)
    }


}