import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Profile } from '../model/Profile';

import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.css']
})
export class CadastrarComponent implements OnInit {

  profile: Profile = new Profile
  confirmarSenha: string
  tipo: string

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    window.scroll(0, 0)
  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value
  }

  tipoUser(event: any) {
    this.tipo = event.target.value
  }

  cadastrar() {
    if (this.profile.profilePassword != this.confirmarSenha) {
      alert("As senhas estão diferentes")
    }
    else {
      this.profile.profileBio = "Olá, estou usando a rede social Oikos!"
      this.authService.cadastrar(this.profile).subscribe((resp: Profile) => {
        this.profile = resp
        this.router.navigate(['/entrar'])
        alert('Usuario cadastrado.')
      })
    }

  }


}
