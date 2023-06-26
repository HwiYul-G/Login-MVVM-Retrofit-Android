## 무엇을 하는가?
Retrofit을 이용한 Login 기능을 MVVM 아키텍처 패턴으로 구현하는 것을 학습<br>
이를 위해 [github](https://github.com/muhammadAriefHidayat/Login-Register-MVVM-Retrofit) 누군가의 github 코드를 분석하며 학습한다.

## 배울 내용
<ul>
  <li>ViewModel</li>
  <li>DataSource</li>
  <li>Repository</li>
</ul>

## 연결 과정
<ul>
  <li>ApiConfig Class에서 Retrofit 객체를 생성하는 getInstance()가 싱글톤으로 생성된다.</li>
  <li>이때 Injection object에서 provideRepository(context :Context) : MainRepository 메소드가 있다. 이 메소드는 apiService를 생성해서, remoteDataSource로 apiService의 인자를 넘겨 MainRepository를 return한다.</li>
  <li>한편, Activity(View)-ViewModel-Repository-DataSource(retrofit 통신 등이 속함)으로 순서대로 따라가보자.</li>
  <li>Activity에 ViewModel이 있다. 이 ViewModel은 ViewModelFactory라는 class를 통해 생성된다. </li>
  <li> ViewModel을 생성하는 ViewModelFactory class는 ViewModel을 상속받은 class를 생성하는 create 함수를 가졌고 ViewModelFactory 자기 자신은 싱글톤으로 생성되게 구현된다.</li>
  <li> 이때 ViewModelFactory에서 인스턴스를 생성할 때 mainRepository를 이미 넣어준다.</li>
  <li> 그래서 Activity에서 ViewModel(여기서는 LoginViewModel)의 login을 실행하게 되면, repository의 login이 수행된다. </li>
  <li> repository의 login은(여기선 MainRepository의 login함수)은 remoteDataSource를 파라미터로 받고 remoteDataSource의 login을 수행시킨다.</li>
  <li> remoteDatSource의 login은 ApiService를 인자로 받고 있는데, ApiService interface에 실질적인 retorift으로 통신하는 함수 @Post 등이 있다. </li>
  <li> remoteDataSource에서 ApiService interface의 함수를 실행시킬 때 코루틴을 사용해야 한다. 여기서 flow와 emit을 썼다. </li>
  <li> remoteDataSource의 결과가  LoginViewModel에서 collect된다. 이때 Call<LoginResponse>로 받아서 Callback<LoginREs>로 enqueu시키면 onResponse와 onFailure 처리가 된다.</li>
  <li> onResponse가 잘 된다면 Activity에도 잘 전달 된 것이다!</li>
</ul>
