export interface NetworkKitPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
