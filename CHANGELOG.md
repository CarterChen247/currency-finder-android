# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## 0.1.0 (2024-12-28)


### Features

* add action buttons for currency list filtering ([70b904d](https://github.com/CarterChen247/currency-finder-android/commit/70b904def134151e9c5cd9c3e796a1609c5bc74e))
* add CurrencyInfo model and integrate it into a list ([77f47a2](https://github.com/CarterChen247/currency-finder-android/commit/77f47a2d187fa067d17df886c88dee0f557fbfb3))
* add loading state ([2739e3c](https://github.com/CarterChen247/currency-finder-android/commit/2739e3c51210e07465aef93dad4b4ab525d28fe3))
* add LocalDataSource implementation and test cases using TDD. Create domain models like CurrencyData and CurrencyType ([832b546](https://github.com/CarterChen247/currency-finder-android/commit/832b5461c7cf43c3d0d32514b0a9b0d32596729e))
* add Repository and RepositoryImpl ([727c7b4](https://github.com/CarterChen247/currency-finder-android/commit/727c7b4a41adc0432128353fd49aa143b1c4dec4))
* add timber to print logs ([bbdd944](https://github.com/CarterChen247/currency-finder-android/commit/bbdd944c6d772a527c5984f9418ae06c01ec5893))
* add UserAction handling to CurrencyListScreen and ViewModel ([e144a20](https://github.com/CarterChen247/currency-finder-android/commit/e144a206afdf6d4ebe7dcbe600c6dadee6c7c23d))
* add ViewModel test for CurrencyListViewModel ([dfb8bcc](https://github.com/CarterChen247/currency-finder-android/commit/dfb8bcc0d7d1fb027efedbd57905e1cfaf4f51ea))
* call requestCurrencyList in onResume ([5f09ee9](https://github.com/CarterChen247/currency-finder-android/commit/5f09ee918ef87690129ac2f39326630196fedefa))
* connect CurrencyListViewModel with Repository ([2a59544](https://github.com/CarterChen247/currency-finder-android/commit/2a5954460b4b69526f4f313c70c298497dd5dff3))
* **CurrencyFilter:** add SymbolStartWithRule to CurrencyFilter ([135318d](https://github.com/CarterChen247/currency-finder-android/commit/135318d108a796d509df14ea67aa5a0727207e5f))
* **CurrencyFilter:** extract MatchingRule Interface to modularize matching logic. Add partial match logic with PartialMatchRule ([1d2b14d](https://github.com/CarterChen247/currency-finder-android/commit/1d2b14d5422f877c965fa6357c15c7ee7ef341d3))
* **CurrencyFilter:** implement search logic with CurrencyFilter and its test case ([f16b855](https://github.com/CarterChen247/currency-finder-android/commit/f16b8555a47a8cb4c7e64d23d328e30b8918d573))
* **data:** add datasets for crypto and fiat currency ([793f3c4](https://github.com/CarterChen247/currency-finder-android/commit/793f3c475e78de9a135ff8531a7341531ea294ed))
* **data:** implement `InMemoryDataSourceImpl` with data loading and filtering ([faaa0b7](https://github.com/CarterChen247/currency-finder-android/commit/faaa0b7d8b3d5a9d2601a74fd6d770ee6d0ad82a))
* ensure repository operations are performed on a background thread to avoid blocking the main thread ([335389d](https://github.com/CarterChen247/currency-finder-android/commit/335389d5e18f9ef68ad291a6233f1977d5f12f2e))
* handle only the latest user search request in CurrencyListViewModel ([6ba1b1d](https://github.com/CarterChen247/currency-finder-android/commit/6ba1b1d657455490b6490c66c5a2bf6f7e066b54))
* implement conversion between CurrencyData and CurrencyInfo with test cases ([3bbda72](https://github.com/CarterChen247/currency-finder-android/commit/3bbda72da0ed60cd9322d7962c445602f1f65dfc))
* implement LocalDataSourceImpl with Room ([b7e1525](https://github.com/CarterChen247/currency-finder-android/commit/b7e1525f906d7b6fb78023abff213caec3e11ce1))
* integrate Hilt for Dependency Injection ([f1b4900](https://github.com/CarterChen247/currency-finder-android/commit/f1b4900172174d8208753edc76a04e4abf9cd85b))
* **navigation:** integrate Jetpack Navigation component ([942d03f](https://github.com/CarterChen247/currency-finder-android/commit/942d03f4c3403a3eb46216f2839abff2903fd4c4))
* **ui:** add search bar to CurrencyListScreen ([ec9b6bb](https://github.com/CarterChen247/currency-finder-android/commit/ec9b6bb5f0ea3f7cfa9c34f095812ef49ed97bc4))
* **ui:** adjust paddings ([a37e513](https://github.com/CarterChen247/currency-finder-android/commit/a37e51333783ae4060d760301a332d734481c0e6))
* **ui:** improve keyboard handling in CurrencyListScreen and DemoActivity ([166f261](https://github.com/CarterChen247/currency-finder-android/commit/166f26104fd75017d40570b91b2e2b4c466a1515))
