import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileLoginDTO } from '../model/ProfileLoginDTO';
import { AuthService } from '../service/auth.service';
import { environment } from './../../environments/environment.prod';

@Component({
  selector: 'app-entrar',
  templateUrl: './entrar.component.html',
  styleUrls: ['./entrar.component.css']
})
export class EntrarComponent implements OnInit {

  profileLoginDto: ProfileLoginDTO = new ProfileLoginDTO();

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  signIn() {
    this.auth.signIn(this.profileLoginDto).subscribe((resp: ProfileLoginDTO) => {
      this.profileLoginDto = resp;
      this.router.navigate(["/home"]);

      environment.token = this.profileLoginDto.profileToken;
      environment.pic = this.profileLoginDto.profilePic;
      environment.id = this.profileLoginDto.profileId;

    }, err => {
      if (err.status == 401) {
        alert("Usuário ou senha inválidos!");
      }
    });
  }


}
