import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { CommunityComponent } from './community/community.component';
import { EntrarComponent } from './entrar/entrar.component';
import { HomeComponent } from './home/home.component';
import { MenssageContentComponent } from './menssage-content/menssage-content.component';
import { MessageComponent } from './message/message.component';

const routes: Routes = [
  {path: "", redirectTo: "entrar", pathMatch: "full"},
  {path: "entrar", component: EntrarComponent},
  {path: "cadastrar", component: CadastrarComponent},
  {path: "home", component: HomeComponent},
  {path: "menssage-content", component: MenssageContentComponent},
  {path: "community", component: CommunityComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
