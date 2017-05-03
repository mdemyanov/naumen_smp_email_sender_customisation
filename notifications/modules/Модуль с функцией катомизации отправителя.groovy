/*! UTF8 */
//Автор: mdemyanov
//Дата создания: 02.05.17
//Код: 
//Назначение: 
/**
 * Модуль с функцией катомизации отправителя
 */
//Версия: 4.6.*
//Категория: 
//Параметры------------------------------------------------------
//Функции--------------------------------------------------------
/**
 * Функция, которая возвращает параметры для соглашения.
 */
def agreeParams() {
	return [
			mailHolder:'agreement',
			params:[
			        from:'fromEmail',
			        name:'fromName'
			]
	]
}
/**
 * Функция, которая устанавливает параметры отправителя
 * в зависимости от данных из соглашения.
 * Функция принимает на вход:
 * @param notification - контекстная переменная скрипта кастомизации notification
 * @param obj - объект, отновистельно которого необходимо произвести вычисления
 * Функция получает объект с адресом (соглашение), затем перезаписывает
 * непустые параметры отправителя.
 */
def setAddressFromAgreement(notification, obj) {
	def address = agreeParams()
	def mailHolder = obj[address.mailHolder]
	if (mailHolder) {
		address.params.each{target, source ->
			if (mailHolder[source]) {
				notification.parameters[target] = mailHolder[source]
			}
		}
	}
}
//Основной блок -------------------------------------------------
//Пример использования:
//modules.moduleName.setAddressFromAgreement(notification, subject)
//где notification - контектсная переменная notification, subject -
//текущий объект, на котором выполняется оповещение