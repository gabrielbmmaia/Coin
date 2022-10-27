# Coin

#### O app coin tem a finalidade de converter mais de 30 câmbios em tempo real! Este projeto foi criado para por em prática tudo que aprendi sobre Clean Architecture + MvvM + Koin + Retrofit. Então, além de mostrar as funcionalidades do projeto, também quero disponibilziar com vocês o sketch que fiz a respeito das organizações das pastas do projeto, além de te convidar a ver todas as anotações que escrevi dentro das classes. :books:

## :iphone: Layout do Projeto
![layout-coin](https://user-images.githubusercontent.com/109977155/198406279-4d2c2d72-fd86-4a4f-bc23-bab9bcf04798.png)


## :pencil2: Organização das Pastas
![organizacao-coin](https://user-images.githubusercontent.com/109977155/198405661-754ff472-71a0-4744-9433-96a136a5bc45.png)

## 🔨 Funcionalidades do projeto

`Converter Valor` <br>

## ✔️ Técnicas e tecnologias utilizadas

`Kotlin`: linguagem utilizada no projeto. <br>
`ViewBinding`: fazer binding de view. <br>
`Flow`: fazer atualizações da tela em tempo real. <br>
`Courutines`: usado para acessar o banco de dados em uma thread adicional. <br>
`Git`: usado para versionar o código no github. <br>
`Retrofit`: usado para se comunicar com a API. <br>
`Koin`: usado para injeção de dependência. <br>
`Arquitetura Clean + MvvM`: projeto foi feito totalmente nas duas arquiteturas. <br>
`State Pattern` <br>
`Observer Pattern`

## :earth_americas: Api utilizada 27/10/2022

https://apilayer.com/marketplace/exchangerates_data-api

## 🛠️ Abrir e rodar o projeto

Após baixar o projeto, você pode abrir com o Android Studio. Para isso, na tela de launcher clique em:
Open an Existing Project (ou alguma opção similar) Procure o local onde o projeto está e o selecione (Caso o projeto seja baixado via zip,
é necessário extraí-lo antes de procurá-lo) Por fim clique em OK O Android Studio deve executar algumas tasks do Gradle para configurar o projeto, 
aguarde até finalizar. Ao finalizar as tasks, você precisa criar uma chave de API e colocar dentro do arquivo "core.Constants" e coloque a sua chave no local indicado abaixo:

```
object Constants {

    const val API_KEY = "COLOQUE-SUA-API-AQUI"
    const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"
    const val OK_HTTP = "ok http"

}
```

## 🎥 Showcase do aplicativo

https://user-images.githubusercontent.com/109977155/198408321-caa0941c-8866-4f43-97ca-8fa1aaa5f400.mp4
