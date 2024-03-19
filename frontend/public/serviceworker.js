// 캐싱할 파일 목록
const CACHE_NAME = 'v1';
const urlsToCache = [
  '/',
  '/index.html',
  // 추가적으로 캐싱할 리소스를 여기에 추가
];

// Service Worker 설치 시, 앱의 핵심 리소스를 캐시에 저장
self.addEventListener('install', event => {
  event.waitUntil(
    caches.open(CACHE_NAME)
      .then(cache => {
        console.log('Opened cache');
        return cache.addAll(urlsToCache);
      })
  );
});

// 요청을 가로채 캐시에서 찾아보고, 없으면 네트워크에서 가져옴
self.addEventListener('fetch', event => {
  event.respondWith(
    caches.match(event.request)
      .then(response => {
        // 캐시에서 찾으면 그것을 반환, 아니면 네트워크 요청을 수행
        return response || fetch(event.request);
      })
  );
});

// 서비스 워커 업데이트 처리, 오래된 캐시 제거
self.addEventListener('activate', event => {
  const cacheWhitelist = [CACHE_NAME];
  event.waitUntil(
    caches.keys().then(cacheNames => {
      return Promise.all(
        cacheNames.map(cacheName => {
          if (cacheWhitelist.indexOf(cacheName) === -1) {
            // 화이트리스트에 없는 캐시 이름을 가진 캐시 삭제
            return caches.delete(cacheName);
          }
        })
      );
    })
  );
});
