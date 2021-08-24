import { registerPlugin } from '@capacitor/core';

import type { NetworkKitPlugin } from './definitions';

const NetworkKit = registerPlugin<NetworkKitPlugin>('NetworkKit', {
  web: () => import('./web').then(m => new m.NetworkKitWeb()),
});

export * from './definitions';
export { NetworkKit };
