import { WebPlugin } from '@capacitor/core';

import type { NetworkKitPlugin } from './definitions';

export class NetworkKitWeb extends WebPlugin implements NetworkKitPlugin {
  get(_options: { url: string; endPoint: string; params: string; }): Promise<{ res: string; }> {
    throw new Error('Method not implemented.');
  }
 
}
