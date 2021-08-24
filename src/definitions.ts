export interface NetworkKitPlugin {
  echo(options: {url: string ,endPoint:string,params:string}): Promise<{ res: any }>;
  
}
