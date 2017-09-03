import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule }   from '@angular/forms';

import { AppComponent } from './app.component';

import { ServicesModule } from './services/services.module';
import { LayoutModule } from './layout/layout.module';

import { LayoutCommunComponent } from './layout/layout-commun/layout-commun.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { TournoisComponent } from './tournois/tournois.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AProposComponent } from './a-propos/a-propos.component';
import { ContactComponent } from './contact/contact.component';

const appRoutes: Routes = [
  { path: '', component: LayoutCommunComponent,
    children: [
        { path: '', component: HomeComponent },
        { path: 'tournois', component: TournoisComponent },
        { path: 'contact', component: ContactComponent },
        { path: 'a-propos', component: AProposComponent }
      ]
  },
  { path: 'login', component: LoginComponent },
  { path: '**', component: PageNotFoundComponent }
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
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    ServicesModule,
    LayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
