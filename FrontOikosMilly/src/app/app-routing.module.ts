import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { CommunityComponent } from './community/community.component';
import { DeleteCommunityComponent } from './delete/delete-community/delete-community.component';
import { MessageDeleteComponent } from './delete/message-delete/message-delete.component';
import { MessageEditComponent } from './edit/message-edit/message-edit.component';
import { EntrarComponent } from './entrar/entrar.component';
import { HomeComponent } from './home/home.component';
import { MenssageContentComponent } from './menssage-content/menssage-content.component';

const routes: Routes = [
  {path: "", redirectTo: "entrar", pathMatch: "full"},
  {path: "entrar", component: EntrarComponent},
  {path: "cadastrar", component: CadastrarComponent},
  {path: "home", component: HomeComponent},
  {path: "menssage-content", component: MenssageContentComponent},
  {path: "community", component: CommunityComponent},
  {path: "message-delete/:id", component: MessageDeleteComponent},
  {path: "message-edit/:id", component: MessageEditComponent},
  {path: "delete-community/:id", component: DeleteCommunityComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
