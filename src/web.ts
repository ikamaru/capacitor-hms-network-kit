import { WebPlugin } from '@capacitor/core';

import type { NetworkKitPlugin } from './definitions';

export class NetworkKitWeb extends WebPlugin implements NetworkKitPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
