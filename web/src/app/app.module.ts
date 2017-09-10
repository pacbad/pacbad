import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule }   from '@angular/forms';

import { AppComponent } from './app.component';

import { ServicesModule } from './services/services.module';
import { LayoutModule } from './layout/layout.module';

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

import {TournoiService} from './tournois/tournoi.service';

const appRoutes: Routes = [
  { path: '', component: LayoutCommunComponent,
    children: [
        { path: '', component: HomeComponent },
        { path: 'tournois', component: TournoisComponent },
        { path: 'contact', component: ContactComponent },
        { path: 'a-propos', component: AProposComponent },
        { path: 'register', component: RegisterComponent },
        { path: 'compte', component: MonCompteComponent },
      ]
  },
  { path: 'login', component: LoginComponent },
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
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    ServicesModule,
    LayoutModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthHttpInterceptor, multi: true },
    TournoiService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
