＃シンプルなクローラーのデモ
1. JDK1.8
2. springboot 2.1.5
3. webmagic 0.7.3
4. mongodb 4.0.2
5. swagger2 2.9.2

## MongoDBの単純な構文
**バージョン情報を表示**
> db.version（）

**すべてのデータベースを表示**
>データベースを表示

データベースの切り替え
[db_name]を使用

現在のデータベースを表示する
db

現在のデータベースを削除する
db.dropDatabase（）

コレクションを見る
コレクションを表示

コレクションを削除
db.test.drop（）

ドキュメントを挿入
db.test.insert（[
{name： "test1"}、
{name： "test2"}
]）

ドキュメントを削除
db.test.remove（（））
db.test.remove（{name： "Test 2"}）

ドキュメントのクエリ
db.test.find（）
db.test.find（{名前： "テスト2"}）