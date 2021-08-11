import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { CommunityPageComponent } from './community-page/community-page.component';
import { CommunityComponent } from './community/community.component';
import { EntrarComponent } from './entrar/entrar.component';
import { HomeComponent } from './home/home.component';
import { MessageComponent } from './message/message.component';
import { ProfilePageComponent } from './page/profile-page/profile-page.component';

const routes: Routes = [

  {path: "", redirectTo: "entrar", pathMatch: "full"},
  {path: "entrar", component: EntrarComponent},
  {path: "cadastrar", component: CadastrarComponent},
  {path: "home", component: HomeComponent},
  {path: "community", component: CommunityComponent},
  {path: "message", component: MessageComponent},
  {path: "community-page/:id", component: CommunityPageComponent},
  {path: "profile-page/:id", component: ProfilePageComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
