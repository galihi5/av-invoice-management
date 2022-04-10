# Aplikasi Invoice Management #

Aplikasi ini dipakai untuk mengelola invoice dan menyambunkan dengan berbagai metode pembayaran masa kini.
Diantara metode pembayaran yang akan disupport antara lain :

* Virtual Account Bank
  * Bank BNI
  * Bank CIMB
  * Bank BSI

* e-Wallet
  * Ovo
  * GoPay

* QR Payment
  * QRIS

Tipe tagihan yang tersedia:
   * CLOSED : bayar sesuai nominal. Kalau tidak sesuai, ditolak
   * OPEN : pembayaran berapapun diterima
   * INSTALLMENT : pembaran diterima selama total akumulasi lebih kecil atau sama dengan nilai tagihan

Fitur Aplikasi :
* Manajemen Customer
  * Registrasi Customer
  * Rekap tagihan Customer
  * Historu pembayaran
* Manajemen invoice
    * Membuat invoice
    * Mengganti nilai dan tanggal jatuh tempo
    * Membatalkan invoice
* 
  

## Cara Setup Database
1. Jalankan postgresql di docker

   Linux

    ```
    docker run --rm \
    --name invoice-db \
    -e POSTGRES_DB=invoicedb \
    -e POSTGRES_USER=invoice \
    -e POSTGRES_PASSWORD=invoice123 \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -v D:\Documents\Projects\Databases\Postgres\pvolume:/var/lib/postgresql/data \
    -p 5432:5432 \
    postgres:14.1-alpine
    ```
   Windows
    ```
    docker run --rm --name invoice-db -e POSTGRES_DB=invoicedb -e POSTGRES_USER=invoice -e POSTGRES_PASSWORD=invoice123 -e PGDATA=/var/lib/postgresql/data/pgdata -v D:\Documents\Projects\Databases\Postgres\pvolume:/var/lib/postgresql/data -p 5432:5432 postgres:14.1-alpine
    ```

 