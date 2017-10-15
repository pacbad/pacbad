import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule }   from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatAutocompleteModule, MatInputModule} from '@angular/material';

import { AppComponent } from './app.component';

import { ServicesModule } from './services/services.module';
import { LayoutModule } from './layout/layout.module';
import { CommunModule } from './commun/commun.module';

import { LayoutCommunComponent } from './layout/layout-commun/layout-commun.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { TournoisComponent } from './tournois/tournois.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AProposComponent } from './a-propos/a-propos.component';
import { ContactComponent } from './contact/contact.component';

import { AuthHttpInterceptor } from './services/authentification.interceptor';
import { MonCompteComponent } from './mon-compte/mon-compte.component';
import { ErreurComponent } from './erreur/erreur.component';

import {TournoiService} from './tournois/tournoi.service';
import { TournoiComponent } from './tournois/tournoi/tournoi.component';
import { AdminComponent } from './admin/admin.component';
import { AdminService } from './admin/admin.service';
import { AccesInterditComponent } from './acces-interdit/acces-interdit.component';

const appRoutes: Routes = [
  { path: '', component: LayoutCommunComponent,
    children: [
        { path: '', component: HomeComponent },
        { path: 'tournois', component: TournoisComponent, data: { title: 'Tournois' } },
        { path: 'tournoi/:id', component: TournoiComponent, data: { title: 'Tournoi' } },
        { path: 'contact', component: ContactComponent, data: { title: 'Contact' } },
        { path: 'a-propos', component: AProposComponent, data: { title: 'A propos' } },
        { path: 'register', component: RegisterComponent, data: { title: 'Créer un compte' } },
        { path: 'compte', component: MonCompteComponent, data: { title: 'Mon compte', login: true } },
        { path: 'admin', component: AdminComponent, data: { title: 'Administration', login: true } },
      ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'erreur', component: ErreurComponent },
  { path: 'acces-interdit', component: AccesInterditComponent },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    TournoisComponent,
    PageNotFoundComponent,
    AProposComponent,
    ContactComponent,
    RegisterComponent,
    MonCompteComponent,
    ErreurComponent,
    TournoiComponent,
    AdminComponent,
    AccesInterditComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatAutocompleteModule,
    RouterModule.forRoot(appRoutes),
    ServicesModule,
    LayoutModule,
    CommunModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthHttpInterceptor, multi: true },
    TournoiService,
    AdminService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
