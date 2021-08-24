export interface NetworkKitPlugin {
  get(options: {url: string ,endPoint:string,params:string}): Promise<{ res: string }>;
  
}
