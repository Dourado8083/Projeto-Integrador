import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactComponent } from './contact/contact.component';
import { EntrarComponent } from './entrar/entrar.component';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { FormsModule } from '@angular/forms';
import { CommunityComponent } from './community/community.component';
import { MenssageContentComponent } from './menssage-content/menssage-content.component';
import { MessageDeleteComponent } from './delete/message-delete/message-delete.component';
import { MessageEditComponent } from './edit/message-edit/message-edit.component';
import { DeleteCommunityComponent } from './delete/delete-community/delete-community.component';
import { EditCommunityComponent } from './edit/edit-community/edit-community.component';
@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    HomeComponent,
    FooterComponent,
    AboutUsComponent,
    ContactComponent,
    EntrarComponent,
    CadastrarComponent,
    CommunityComponent,
    MenssageContentComponent,
    MessageDeleteComponent,
    MessageEditComponent,
    DeleteCommunityComponent,
    EditCommunityComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
