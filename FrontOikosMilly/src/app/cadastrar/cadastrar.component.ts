import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Profile } from '../model/profile';
import { profileLoginDTO } from '../model/profileLogin';
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
    window.scroll(0,0)
  }

  confirmSenha(event: any)
  {
    this.confirmarSenha = event.target.value
  }

  tipoUser(event: any)
  {
    this.tipo = event.target.value
  }

  cadastrar() {
    console.log(this.profile.profilePassword)
    console.log(this.confirmarSenha)
    if(this.profile.profilePassword != this.confirmarSenha)
   {
    alert("As senhas estão diferentes")
   }
   else
   {
     this.authService.cadastrar(this.profile).subscribe((resp: Profile)=>{
       this.profile = resp
       this.router.navigate(['/entrar'])
       alert('Usuario cadastrado.')
     })
   }
  
  }


}
