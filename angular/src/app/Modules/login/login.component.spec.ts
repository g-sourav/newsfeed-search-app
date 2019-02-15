import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { LoginService } from 'src/app/Services/login.service';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      providers: [LoginService],
      imports: [ReactiveFormsModule, HttpClientModule, RouterTestingModule]
    })
    .compileComponents();
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

 

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('is form valid', () =>{

    let email=component.loginForm.controls['userEmail'];
    email.setValue("asd@asd.asd");

    let pass=component.loginForm.controls['userPassword'];
    pass.setValue("Asd123!!");

    expect(component.loginForm.valid).toBeTruthy();
  });

  it('is form invalid - invalid data', () =>{

    let email=component.loginForm.controls['userEmail'];
    email.setValue("");

    let pass=component.loginForm.controls['userPassword'];
    pass.setValue("1Aa!");

    expect(component.loginForm.valid).toBeFalsy();
  });

  it('is form invalid - invalid email', () =>{

    let email=component.loginForm.controls['userEmail'];
    email.setValue("aaaa");

    let pass=component.loginForm.controls['userPassword'];
    pass.setValue("1Aaaaaaa");

    expect(component.loginForm.valid).toBeFalsy();
  });

});
