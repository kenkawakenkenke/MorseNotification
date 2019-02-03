# Morse Notification

## What is this?

Morse Notification ([Play Store](https://play.google.com/store/apps/details?id=com.ken.morse)) is an Android app that plays your notifications as vibrations encoded in Morse Code; which means is someone sends you an SMS saying "SOS", your phone will buzz in the famous "・・・ーーー・・・" pattern.

### But.. why?

I made this app when I was expecting an important email, and found myself stopping at every street corner during my commutes to look at the phone everytime it buzzed.
I wanted to create a way to let myself (sort of) know what messages I'm getting without looking at my phone.

### But.. wireless earphones?

I don't like walking outside with earphones.

## Technical details

Alpha-numerics are encoded into [ITU standard Morse Code](https://en.wikipedia.org/wiki/Morse_code#Development_and_history).
Dots are 100ms. (If you want to change this, feel free to send a PR).

## 日本語は？

[Kuromoji](https://www.atilika.com/ja/kuromoji/)で日本語を読みに変換したあと[和文モールス符号](https://ja.wikipedia.org/wiki/%E3%83%A2%E3%83%BC%E3%83%AB%E3%82%B9%E7%AC%A6%E5%8F%B7#%E3%82%A4%E3%83%AD%E3%83%8F)にエンコードしています。
Wikipediaいわく、和文の場合は英文字を()で囲まないといけないらしいのですが、面倒なので現バージョンはそのまま混じってます。
正直作者はモールス信号がわからないので、違和感を覚えません。しってる人は怒るのかも。

## Why are you making this repository public? なんでソース公開してんの？

Morse Notification requires you to enable permissions for "Notification Access"; this allows the app 
to see any notification that comes to your phone, including phone calls, SMS, and emails. You need 
to be trusting the app a lot to give that permission. The only way that I would trust an app would be
if I could see the code.

このアプリを使うには「通知アクセス」許可を与える必要があります。これは、あなたのスマホに来るすべての通知を読む権限を
当アプリに与えるということです。（つまり、受信した電話やメールの中身まで。）僕だったらソースを読まないと信用できないので、
ソースを公開しました。

## Who made this?

[Ken](http://twitter.com/kenkawakenkenke) is a software engineer who makes [many sort-of useful things](http://kawamoto.co.uk).
